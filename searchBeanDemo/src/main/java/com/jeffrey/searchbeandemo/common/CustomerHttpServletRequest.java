package com.jeffrey.searchbeandemo.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;

/**
 * @author jeffrey
 * @version 1.0
 * @date 2023/12/18
 * @time 17:19
 * @week 星期一
 * @description  可以自己在后台设置param,比如coCode,whCode 这种直接从session里面取的，不需要从前端传过来的参数，后端自己设置就行
 **/
public class CustomerHttpServletRequest extends HttpServletRequestWrapper {
    private Map<String, String[]> params;

    public CustomerHttpServletRequest(HttpServletRequest request, Map<String, String[]> newParams) {
        super(request);
        if (SysUtil.isNull(newParams)) {
            newParams = new HashMap<>();
        }
        this.params = newParams;
        renewParameterMap(request);
    }


    public CustomerHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.params = new HashMap<>();
        renewParameterMap(request);
    }
    @Override
    public String getParameter(String name) {
        String result = "";

        Object v = params.get(name);
        if (v == null) {
            result = null;
        } else if (v instanceof String[]) {
            String[] strArr = (String[]) v;
            if (strArr.length > 0) {
                result = strArr[0];
            } else {
                result = null;
            }
        } else if (v instanceof String) {
            result = (String) v;
        } else {
            result = v.toString();
        }

        return result;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return new Vector<String>(params.keySet()).elements();
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] result = null;

        Object v = params.get(name);
        if (v == null) {
            result = null;
        } else if (v instanceof String[]) {
            result = (String[]) v;
        } else if (v instanceof String) {
            result = new String[]{(String) v};
        } else {
            result = new String[]{v.toString()};
        }

        return result;
    }

    private void renewParameterMap(HttpServletRequest req) {

        String queryString = req.getQueryString();

        if (queryString != null && queryString.trim().length() > 0) {
            String[] params = queryString.split("&");

            for (int i = 0; i < params.length; i++) {
                int splitIndex = params[i].indexOf("=");
                if (splitIndex == -1) {
                    continue;
                }

                String key = params[i].substring(0, splitIndex);

                if (!this.params.containsKey(key)) {
                    if (splitIndex < params[i].length()) {
                        String value = params[i].substring(splitIndex + 1);
                        value = URLDecoder.decode(value);
                        this.params.put(key, new String[]{value});
                    }
                }
            }
        }
    }
}

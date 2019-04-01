package com.project.doer.model;

import java.util.List;

public class DoerResponse<T> {
    public List<T> getData() {
        return data;
    }

    private List<T> data;


}

package com.traderacademy.http.model;

/**
 * Created by Eric
 * on 2016/5/11
 * for project TRC
 */
public class TestResultParam extends ResultParam {
    @Override
    public testdata getData() {
        return data;
    }

    public void setData(testdata data) {
        this.data = data;
    }

    private testdata data;


    public class testdata {
        public model getModelInfo() {
            return ModelInfo;
        }

        public void setModelInfo(model modelInfo) {
            ModelInfo = modelInfo;
        }

        public model ModelInfo;
    }

    public class model {
        public Integer id;
        public Integer user_id;
        public String real_name = "";
    }
}

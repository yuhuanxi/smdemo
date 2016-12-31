/**
 *
 */
package com.ysp.smdemo.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ysp.smdemo.common.utils.PagingDto;
import com.ysp.smdemo.common.utils.ReturnCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author shipeng.yu
 * @version V1.0
 * @date 2015年8月28日 上午9:53:37
 * @description 基础控制器
 */
public class BaseController {

    protected static BaseAjaxResult renderJsonSuccess() {
        return new BaseAjaxResult(true, ReturnCode.SUCCESS.getCode(), ReturnCode.SUCCESS.getMsg());
    }

    protected static BaseAjaxResult renderJsonFail() {
        return new BaseAjaxResult(false, ReturnCode.FAIL.getCode(), ReturnCode.FAIL.getMsg());
    }

    protected static BaseAjaxResult renderJsonFail(int code, String msg) {
        return new BaseAjaxResult(false, code, msg);
    }

    protected static AjaxResult renderJsonAjaxResult(Object obj) {
        return new AjaxResult(obj);
    }

    protected static AjaxResult renderJsonAjaxResult(int code, String msg, Object obj) {
        return renderJsonAjaxResult(true, code, msg, obj);
    }

    protected static AjaxResult renderJsonAjaxResult(boolean success, int code, String msg, Object obj) {
        return new AjaxResult(success, code, msg, obj);
    }

    protected static AjaxPageResult renderJsonAjaxPageResult(boolean success, int code, String msg, Object res, int curPage,
                                                             int pageCount, long count, boolean hasRecords) {
        return new AjaxPageResult(success, code, msg, res, curPage, pageCount, count,
                hasRecords);
    }

    protected static AjaxPageResult renderJsonAjaxPageResult(boolean success, int code, String msg, Object res,
                                                             PagingDto pagingDto) {
        return new AjaxPageResult(success, code, msg, res, pagingDto.getCurPage(), pagingDto
                .getPageCount(), pagingDto.getCount(), pagingDto.hasRecords());
    }

    /**
     * @author shipeng.yu
     * @version V1.0
     * @date 2015年8月29日 下午2:59:19
     * @description
     */
    @JsonAutoDetect
    public static class BaseAjaxResult implements Serializable {

        @JsonProperty
        private boolean success = true;

        @JsonProperty
        private int code = 200;

        @JsonProperty
        private String message = StringUtils.EMPTY;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public BaseAjaxResult() {
        }

        public BaseAjaxResult(String message) {
            this.message = message;
        }

        public BaseAjaxResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public BaseAjaxResult(boolean success, int code, String message) {
            this.success = success;
            this.code = code;
            this.message = message;
        }

    }

    @JsonAutoDetect
    public static class AjaxResult extends BaseAjaxResult {

        /**
         * 结果
         */
        @JsonProperty
        private Object results;

        public Object getResults() {
            return results;
        }

        public void setResults(Object results) {
            this.results = results;
        }

        public AjaxResult(Object res) {
            super(true, 200, null);
            this.results = res;
        }

        public AjaxResult(Boolean success, String message, Object res) {
            super(success, message);
            this.results = res;
        }

        public AjaxResult(int code, String message, Object res) {
            this(true, code, message, res);
        }

        public AjaxResult(Boolean success, int code, String message, Object res) {
            super(success, code, message);
            this.results = res;
        }

    }

    @JsonAutoDetect
    public static class AjaxPageResult extends AjaxResult {
        /**
         * 当前页
         */
        @JsonProperty
        private int curPage = 1;

        /**
         * 总页数
         */
        @JsonProperty
        private int pageCount = 1;

        /**
         * 总数
         */
        @JsonProperty
        private long count = 0;

        /**
         * 是否还有结果
         */
        @JsonProperty
        private boolean hasRecords = true;

        public AjaxPageResult(Object res, int curPage, int pageCount, long count, boolean hasRecords) {
            super(res);
            this.curPage = curPage;
            this.pageCount = pageCount;
            this.count = count;
            this.hasRecords = hasRecords;
        }

        public AjaxPageResult(boolean success, int code, String msg, Object res, int curPage, int pageCount,
                              long count, boolean hasRecords) {
            super(success, code, msg, res);
            this.curPage = curPage;
            this.pageCount = pageCount;
            this.count = count;
            this.hasRecords = hasRecords;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public long getCount() {
            return count;
        }

        public void setCount(long count) {
            this.count = count;
        }

        public boolean isHasRecords() {
            return hasRecords;
        }

        public void setHasRecords(boolean hasRecords) {
            this.hasRecords = hasRecords;
        }

    }

}

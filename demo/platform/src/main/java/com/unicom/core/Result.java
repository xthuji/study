package com.unicom.core;

public enum Result {
    /**
     * 成功
     */
    success("成功", 0),
    /**
     * 失败
     */
    failure("失败", 1),
    
    /**
     * 错误
     */
    Error("错误",9);

    /**
     * 值
     */
    private String label;

    /**
     * 编号
     */
    private int code;

    /**
     * 
     * @param label
     * @param code
     */
    private Result(String label, int code) {
        this.code = code;
        this.label = label;
    }

    public static String getLabel(int code) {
        for (Result t : Result.values()) {
            if (t.getCode() == code) {
                return t.label;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    /**
     * 比较
     * @param code
     * @return
     */
    public boolean compare(int code) {
        if (this.code == code) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 比较
     * @param _code
     * @return
     */
    public boolean compare(String _code) {
        int code;
        
        try 
        {
             code = Integer.parseInt(_code);
        
        }catch (NumberFormatException  e) {
            return false;
        }
        
        if (this.code == code) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     *            the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }
}

package com.appsoluteperfection.graphitewear.dtos;

public class ErrorDto
{
    private String message;

    private ErrorsDto[] errors;

    private String code;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public ErrorsDto[] getErrors ()
    {
        return errors;
    }

    public void setErrors (ErrorsDto[] errors)
    {
        this.errors = errors;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", errors = "+errors+", code = "+code+"]";
    }
}

package com.appsoluteperfection.graphite.dtos;

public class ErrorsDto
{
    private String message;

    private String location;

    private String reason;

    private String locationType;

    private String domain;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getReason ()
    {
        return reason;
    }

    public void setReason (String reason)
    {
        this.reason = reason;
    }

    public String getLocationType ()
    {
        return locationType;
    }

    public void setLocationType (String locationType)
    {
        this.locationType = locationType;
    }

    public String getDomain ()
    {
        return domain;
    }

    public void setDomain (String domain)
    {
        this.domain = domain;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", location = "+location+", reason = "+reason+", locationType = "+locationType+", domain = "+domain+"]";
    }
}

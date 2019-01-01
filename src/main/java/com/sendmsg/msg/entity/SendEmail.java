package com.sendmsg.msg.entity;

public class SendEmail {
    private String form;
    private String send;
    private String content;

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SendEmail{" +
                "form='" + form + '\'' +
                ", send='" + send + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

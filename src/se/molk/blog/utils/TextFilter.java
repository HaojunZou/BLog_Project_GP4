package se.molk.blog.utils;

public class TextFilter {
    public String filterHtml(String value){ //html filter to prevent special characters in text
        value = value.replaceAll("&", "&amp;");
        value = value.replaceAll("<", "&lt;");
        value = value.replaceAll(">", "&gt;");
        value = value.replaceAll(" ", "&nbsp;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("\"", "&quot;");
        value = value.replaceAll("\n", "<br/>;");
        return value;
    }
}

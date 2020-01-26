package ru.rashgild.model.promed;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvnXmlDiary {

    /**
     * Constructor.
     */
    public EvnXmlDiary() {
        this.xmlTypeId = "3";
        this.xmlHtmlTemplate = "<div class='printonly' style='display: block;'><div class='template-block' "
                + "id='block_autoname47'><p class='template-block-caption' "
                + "id='caption_autoname47'><span style='font-weight: bold; font-size:14px;'/></p>"
                + "<div class='template-block-data' id='data_autoname47'>{autoname47}</div></div><br/></div>";
        this.xmlTemplateId = 221314;
    }

    @JsonProperty("Evn_id")
    public String evnId;

    @JsonProperty("XmlType_id")
    private String xmlTypeId;

    @JsonProperty("EvnXml_Data")
    private String evnXmlData;

    @JsonProperty("XmlTemplateHtml_HtmlTemplate")
    private String xmlHtmlTemplate;

    @JsonProperty("XmlTemplate_id")
    private Integer xmlTemplateId;

    public String getEvnId() {
        return evnId;
    }

    public void setEvnId(String evnId) {
        this.evnId = evnId;
    }

    /**
     * set and replace evnXmlData.
     *
     * @param evnXmlData evnXmlData
     */
    public void setEvnXmlData(String evnXmlData) {
        this.evnXmlData = "<data><autoname47>" + evnXmlData
                .replace("\\", "/")
                .replace(">", "&gt;")
                .replace("<", "&lt;") + "</autoname47></data>";
    }

    public String getXmlHtmlTemplate() {
        return xmlHtmlTemplate;
    }

}


package tehnosila.tehnosila_automation.pages.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "url",
    "subcategories",
    "kbt",
    "title2",
    "priority",
    "additionCssClass",
    "manualURL",
    "names",
    "outerId",
    "parentOuterId",
    "imageForBlock",
    "totalNumber"
})
public class Categorygson {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
    @JsonProperty("subcategories")
    private Object subcategories;
    @JsonProperty("kbt")
    private Boolean kbt;
    @JsonProperty("title2")
    private Object title2;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("additionCssClass")
    private Object additionCssClass;
    @JsonProperty("manualURL")
    private String manualURL;
    @JsonProperty("names")
    private List<Name> names = null;
    @JsonProperty("outerId")
    private String outerId;
    @JsonProperty("parentOuterId")
    private String parentOuterId;
    @JsonProperty("imageForBlock")
    private Object imageForBlock;
    @JsonProperty("totalNumber")
    private Integer totalNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("subcategories")
    public Object getSubcategories() {
        return subcategories;
    }

    @JsonProperty("subcategories")
    public void setSubcategories(Object subcategories) {
        this.subcategories = subcategories;
    }

    @JsonProperty("kbt")
    public Boolean getKbt() {
        return kbt;
    }

    @JsonProperty("kbt")
    public void setKbt(Boolean kbt) {
        this.kbt = kbt;
    }

    @JsonProperty("title2")
    public Object getTitle2() {
        return title2;
    }

    @JsonProperty("title2")
    public void setTitle2(Object title2) {
        this.title2 = title2;
    }

    @JsonProperty("priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("additionCssClass")
    public Object getAdditionCssClass() {
        return additionCssClass;
    }

    @JsonProperty("additionCssClass")
    public void setAdditionCssClass(Object additionCssClass) {
        this.additionCssClass = additionCssClass;
    }

    @JsonProperty("manualURL")
    public String getManualURL() {
        return manualURL;
    }

    @JsonProperty("manualURL")
    public void setManualURL(String manualURL) {
        this.manualURL = manualURL;
    }

    @JsonProperty("names")
    public List<Name> getNames() {
        return names;
    }

    @JsonProperty("names")
    public void setNames(List<Name> names) {
        this.names = names;
    }

    @JsonProperty("outerId")
    public String getOuterId() {
        return outerId;
    }

    @JsonProperty("outerId")
    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    @JsonProperty("parentOuterId")
    public String getParentOuterId() {
        return parentOuterId;
    }

    @JsonProperty("parentOuterId")
    public void setParentOuterId(String parentOuterId) {
        this.parentOuterId = parentOuterId;
    }

    @JsonProperty("imageForBlock")
    public Object getImageForBlock() {
        return imageForBlock;
    }

    @JsonProperty("imageForBlock")
    public void setImageForBlock(Object imageForBlock) {
        this.imageForBlock = imageForBlock;
    }

    @JsonProperty("totalNumber")
    public Integer getTotalNumber() {
        return totalNumber;
    }

    @JsonProperty("totalNumber")
    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

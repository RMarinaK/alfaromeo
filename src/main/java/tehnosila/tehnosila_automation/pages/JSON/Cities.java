
package tehnosila.tehnosila_automation.pages.JSON;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "idSAP",
	"name",
    "domain",
    "coordinates",
    "hasPointsOfService",
    "priority",
    "phone",
    "names",
    "fias",
    "kladr",
    "prefix",
    "invisible",
    "isActive",
    "freeDeliveryThreshold",
    "zoom",
    "utmCampaign",
    "emails",
    "supervisorEmails",
    "clientServiceEmail",
    "minimumRemain",
    "minimumRemainForCourier",
    "retargetingCode",
    "deliveryFromMainStore",
    "deliveryPeriod",
    "timezone",
    "isPaymentCard",
    "selfDeliveryDescription",
    "courierDeliveryDescription",
    "metroStoreMap",
    "showRegionStores",
    "shopClosingHour",
    "oneClickBuy",
    "ymShopId"
})
public class Cities {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("idSAP")
    private String idSAP;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("domain")
    private String domain;
    
    @JsonProperty("coordinates")
    private Object coordinates;
    
    @JsonProperty("hasPointsOfService")
    private Boolean hasPointsOfService;
    
    @JsonProperty("priority")
    private Integer priority;
    
    @JsonProperty("phone")
    private Object phone;
 
    @JsonProperty("names")
    private Object names;
    
    @JsonProperty("fias")
    private Object fias;
    
    @JsonProperty("kladr")
    private String kladr;
    
    @JsonProperty("prefix")
    private String prefix;
    
    @JsonProperty("invisible")
    private String invisible;
    
    @JsonProperty("isActive")
    private String isActive;

    @JsonProperty("freeDeliveryThreshold")
    private Object freeDeliveryThreshold;
    
    @JsonProperty("zoom")
    private Integer zoom;
    
    @JsonProperty("utmCampaign")
    private Object utmCampaign;
    
    @JsonProperty("emails")
    private Object emails;
    
    @JsonProperty("supervisorEmails")
    private Object supervisorEmails;
    
    @JsonProperty("clientServiceEmail")
    private Object clientServiceEmail;
    
    @JsonProperty("minimumRemain")
    private Integer minimumRemain;
    
    @JsonProperty("minimumRemainForCourier")
    private Integer minimumRemainForCourier;
    
    @JsonProperty("retargetingCode")
    private Object retargetingCode;
    
    @JsonProperty("deliveryFromMainStore")
    private Boolean deliveryFromMainStore;
    
    @JsonProperty("deliveryPeriod")
    private Integer deliveryPeriod;
    
    @JsonProperty("timezone")
    private Integer timezone;
    
    @JsonProperty("isPaymentCard")
    private Boolean isPaymentCard;
    
    @JsonProperty("selfDeliveryDescription")
    private String selfDeliveryDescription;
    
    @JsonProperty("courierDeliveryDescription")
    private String courierDeliveryDescription;
    
    @JsonProperty("metroStoreMap")
    private Object metroStoreMap;
    
    @JsonProperty("showRegionStores")
    private Boolean showRegionStores;
    
    @JsonProperty("shopClosingHour")
    private Integer shopClosingHour;
    
    @JsonProperty("oneClickBuy")
    private Boolean oneClickBuy;
    
    @JsonProperty("ymShopId")
    private Integer ymShopId;
    
    
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("invisible")
    public String getInvisible() {
        return invisible;
    }

    @JsonProperty("invisible")
    public void setInvisible(String invisible) {
        this.invisible = invisible;
    }
    
    @JsonProperty("isActive")
    public String getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}

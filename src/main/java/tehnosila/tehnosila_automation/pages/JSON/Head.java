
package tehnosila.tehnosila_automation.pages.JSON;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cities"
})
public class Head {
	
	@JsonProperty("cities")
    private List<Cities> cities = null;

    @JsonProperty("cities")
    public List<Cities> getCities() {
        return cities;
    }

    @JsonProperty("cities")
    public void setCities(List<Cities> cities) {
        this.cities = cities;
    }

}

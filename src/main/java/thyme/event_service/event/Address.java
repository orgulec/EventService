package thyme.event_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Address {
    private String city;
    private String street;
    private String number;
    private String country;


    @Override
    public String toString() {
        return country + " / " + city + " / " + street + " / " + number;
    }
}

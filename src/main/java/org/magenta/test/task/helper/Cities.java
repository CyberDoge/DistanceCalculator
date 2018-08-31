package org.magenta.test.task.helper;


import org.magenta.test.task.entity.City;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cities")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cities {
    @XmlElement(name = "city")
    public List<City> cityList;

    public Cities() {
    }
}

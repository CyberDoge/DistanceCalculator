package org.magenta.test.task.helper;


import org.magenta.test.task.entity.Distance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "distances")
@XmlAccessorType(XmlAccessType.FIELD)
public class Distances {
    @XmlElement(name = "distance")
    public List<Distance> distanceList;

    public Distances() {
    }
}

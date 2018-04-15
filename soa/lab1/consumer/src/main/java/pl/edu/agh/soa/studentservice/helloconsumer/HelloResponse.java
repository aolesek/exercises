
package pl.edu.agh.soa.studentservice.helloconsumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for helloResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="helloResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HelloResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "helloResponse", propOrder = {
    "helloResponse"
})
public class HelloResponse {

    @XmlElement(name = "HelloResponse")
    protected String helloResponse;

    /**
     * Gets the value of the helloResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHelloResponse() {
        return helloResponse;
    }

    /**
     * Sets the value of the helloResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHelloResponse(String value) {
        this.helloResponse = value;
    }

}


package pl.edu.agh.soa.studentservice.consumer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getStudentByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getStudentByIdResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SingleStudent" type="{http://studentservice.soa.agh.edu.pl/}student" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getStudentByIdResponse", propOrder = {
    "singleStudent"
})
public class GetStudentByIdResponse {

    @XmlElement(name = "SingleStudent")
    protected Student singleStudent;

    /**
     * Gets the value of the singleStudent property.
     * 
     * @return
     *     possible object is
     *     {@link Student }
     *     
     */
    public Student getSingleStudent() {
        return singleStudent;
    }

    /**
     * Sets the value of the singleStudent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Student }
     *     
     */
    public void setSingleStudent(Student value) {
        this.singleStudent = value;
    }

}

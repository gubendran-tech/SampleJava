package com.gubs.JAXB;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * @author gubs
 * 
 */
@XmlRootElement(name = "ARData")
@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperToMarshall<T> {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private List<T> wrapperToMarshall;

  public List<T> getWrapperToMarshall() {
    return wrapperToMarshall;
  }

  public void setWrapperToMarshall(List<T> wrapperToMarshall) {
    this.wrapperToMarshall = wrapperToMarshall;
  }
}

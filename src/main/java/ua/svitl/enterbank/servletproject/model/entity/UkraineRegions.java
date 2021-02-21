package ua.svitl.enterbank.servletproject.model.entity;

import java.io.Serializable;

public class UkraineRegions implements Serializable {

    private static final long serialVersionUID = -7192753529112227777L;

    public UkraineRegions() {
    }

    public UkraineRegions(Integer regionId, String ukraineRegionName) {
        this.regionId = regionId;
        this.ukraineRegionName = ukraineRegionName;
    }

    public UkraineRegions(String ukraineRegionName) {
        this.ukraineRegionName = ukraineRegionName;
    }

    private Integer regionId;
    private String ukraineRegionName;

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setUkraineRegionName(String ukraineRegionName) {
        this.ukraineRegionName = ukraineRegionName;
    }

    public String getUkraineRegionName() {
        return ukraineRegionName;
    }

    @Override
    public String toString() {
        return "UkraineRegions{" +
                "regionId=" + regionId + '\'' +
                "ukraineRegionName=" + ukraineRegionName + '\'' +
                '}';
    }

    /**
     * The region ID is unique for each UkraineRegion. So this should compare region by ID only.
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        return (other instanceof UkraineRegions) && (regionId != null)
                ? regionId.equals(((UkraineRegions) other).regionId)
                : (other == this);
    }

    /**
     * The region ID is unique for each UkraineRegion. So UkraineRegion with same ID should return same hashcode.
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return (regionId != null)
                ? (this.getClass().hashCode() + regionId.hashCode())
                : super.hashCode();
    }

}

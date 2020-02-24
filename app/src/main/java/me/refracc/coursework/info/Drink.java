package me.refracc.coursework.info;

/**
 * @author refracc
 * @version 1.0
 * @since 19.02.2020
 */
public class Drink {

    private String name;
    private String abv;
    private String vol;
    private String resURL;

    private Drink(){}

    /**
     * This is the class constructor for making a new drink.
     * @param name The name of the drink
     * @param abv The ABV of the drink
     * @param vol The volume of the drink (specify units)
     * @param resURL The location of the image.
     */
    public Drink(String name, String abv, String vol, String resURL) {
        this.name = name;
        this.abv = abv;
        this.vol = vol;
        this.resURL = resURL;
    }

    /**
     * This method will return the name of the alcoholic beverage.
     * @return The name of the drink
     */
    public String getName() {
        return name;
    }

    /**
     * This method will set the name of the alcoholic beverage.
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method will return the a.b.v. of a drink.
     * ABV stands for Alcohol By Volume.
     * @return The content of alcohol in the drink.
     */
    public String getAbv() {
        return abv;
    }

    /**
     * This method will set the a.b.v. of a drink.
     * ABV stands for Alcohol By Volume
     * @param abv The content of alcohol in the drink.
     */
    public void setAbv(String abv) {
        this.abv = abv;
    }

    /**
     * This method will get the volume of the drink
     * @return The volume of the drink
     */
    public String getVol() {
        return vol;
    }

    /**
     * This method will set the volume of the drink.
     * @param vol The volume of the drink to be set.
     */
    public void setVol(String vol) {
        this.vol = vol;
    }

    /**
     * This method will get the image resource URL
     * @return the image URL
     */
    public String getResURL() {
        return resURL;
    }

    /**
     * This method will set the image resource URL
     * @param resURL the image URL to be set.
     */
    public void setResURL(String resURL) {
        this.resURL = resURL;
    }
}

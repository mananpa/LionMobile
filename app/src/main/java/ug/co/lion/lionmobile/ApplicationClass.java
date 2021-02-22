package ug.co.lion.lionmobile;

import android.app.Application;

/**
 * Created by Eriq on 11/10/2015.
 */
public class ApplicationClass extends Application {

    String firstName;
    String lastName;
    String gender;
    String email;
    String phoneNumber;
    String physicalAddress;
    String postalAddress;

    public ApplicationClass() {
        super();
    }

    public void setFirstName(String fName){
        firstName = fName;
    }

    public void setLastName(String lName){
        lastName = lName;
    }

    public void setGender(String gEnder){
        gender = gEnder;
    }

    public void setEmail(String Email){
        email = Email;
    }

    public void setPhoneNumber(String phone){
        phoneNumber = phone;
    }

    public void setPhysicalAddress(String phyAddress){
        physicalAddress = phyAddress;
    }

    public void setPostalAddress(String postalAdd){
        postalAddress = postalAdd;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){return this.lastName;}

    public String getGender(){return this.gender;}

    public String getEmail(){return this.email;}

    public String getPhoneNumber(){return this.phoneNumber;}

    public String getPhysicalAddress(){return this.physicalAddress;}

    public String getPostalAddress(){return this.postalAddress;}


}

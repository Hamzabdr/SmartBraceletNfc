package mbds.socialcardprojetnfc;

public class SocialCard {
     private String Email;
     private String Facebook;
     private String Instagram;
     private String Telephone;

    public SocialCard(String email, String facebook, String instagram, String telephone) {
        Email = email;
        Instagram = instagram;
        Facebook = facebook;
        Telephone = telephone;
    }

    public SocialCard() {
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
//TODO Take data from database to gallery
//TODO Verify email in db
//TODO Listview design
//TODO writer tag with settings BONUS
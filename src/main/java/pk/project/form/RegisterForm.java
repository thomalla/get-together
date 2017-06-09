package pk.project.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterForm
{
    @NotEmpty
    private String login = "";

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String surname = "";

    @NotEmpty
    private String email = "";

    @Length(min=8, max=30,message = "Hasło musi mieć od {min} do {max} znaków")
    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPasswordRepeated()
    {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated)
    {
        this.passwordRepeated = passwordRepeated;
    }
}

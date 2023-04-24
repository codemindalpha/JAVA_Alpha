package testcases.C002D_ASSTOPRIVA__CWE766_496;

import testcasesupport.IO;

import java.util.logging.Level;

public class C002D_ASSTOPRIVA__Object_return_01 {
    private UserRole[] userRoles;

    public void setUserRoles (UserRole[] userRoles) {
        /* FLAW : CWE-766,496 */
        this.userRoles = userRoles;
    }

    private void setUserRoles2(UserRole[] userRoles) {
        try {
            this.userRoles = new UserRole[userRoles.length];
            for (int i = 0; i < userRoles.length; i++) {
                this.userRoles[i] = (UserRole) userRoles[i].clone();
            }
        } catch (CloneNotSupportedException e){
            IO.logger.log(Level.WARNING, e.toString());
        }
    }

    public void bad(UserRole[] user) throws CloneNotSupportedException {
       /* FLAW : CWE-766,496 */
       setUserRoles(user);
    }

    public void good(UserRole[] user) {
        setUserRoles2(user);
    }
}

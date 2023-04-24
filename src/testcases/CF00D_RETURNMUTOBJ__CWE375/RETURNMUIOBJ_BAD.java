package CF00D_RETURNMUTOBJ__CWE375;

public class RETURNMUIOBJ_BAD {
    public class Hospital {

        private Patient[] patientList = new Patient[50];

        public Patient[] getPatients() {
            return patientList;
        }
    }
    public class Patient{

    }
}

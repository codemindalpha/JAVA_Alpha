package CF00D_RETURNMUTOBJ__CWE375;

public class RETURNMUIOBJ_GOOD {
    public class Hospital {

        private Patient[] patientList = new Patient[50];
        public Patient[] patientList_copy = new Patient[50];
        public Patient[] getPatients() {
            patientList_copy = patientList.clone();
            return patientList;
        }
    }
    public class Patient{

    }
}

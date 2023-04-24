package testcases.CF014_CWE498_CloneableClass;

public class CF014_CWE498_CloneableClass__simple {
	 		
	public class CloneClass {
        public CloneClass() throws
        java.lang.CloneNotSupportedException{

                Teacher t1 = new Teacher("guddu","22,nagar road");
                // Do some stuff to remove the teacher.
				// Flow
                Teacher t2 = (Teacher)t1.clone();
                System.out.println(t2.name);
				
				Teacher2 tt = new Teacher2("hh","23,abc");
				Teacher2 tt2 = (Teacher2)tt.clone();
        }
        public static void main(String args[]) {

                new CloneClass();
        }
	}
	class Teacher implements Cloneable {

        public Object clone() {

                try {
                        return super.clone();
                }
                catch (java.lang.CloneNotSupportedException e) {

                        throw new RuntimeException(e.toString());
                }
        }
        public String name;
        public String gradeClass;

        public Teacher(String name, String gradeClass) {
                this.name = name;
                this.gradeClass = gradeClass;
        }
	}

	class Teacher2 implements Cloneable {

        public final void clone() throws java.lang.CloneNotSupportedException{
			throw new java.lang.CloneNotSupportedException();
		}
		
        public String name;
        public String gradeClass;

        public void Teacher(String name, String gradeClass) {
                this.name = name;
                this.gradeClass = gradeClass;
        }
	}
}

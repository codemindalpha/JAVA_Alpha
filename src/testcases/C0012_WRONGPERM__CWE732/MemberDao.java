package testcases.C0012_WRONGPERM__CWE732;


public interface MemberDao {
	void insertMember(MemberModel memberModel);
	void deleteMember(MemberModel memberModel);
	void updateMember(MemberModel memberModel);
	MemberModel selectMember(String userId);
	
	
}

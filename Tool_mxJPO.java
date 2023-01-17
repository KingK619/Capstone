import matrix.db.*;
import java.io.*;
import com.matrixone.apps.domain.DomainObject;
import matrix.util.StringList;
import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.util.PropertyUtil;
import java.util.Map;
import com.matrixone.apps.domain.util.MapList;
import com.matrixone.apps.domain.util.MailUtil;
public  class Tool_mxJPO 
{
	public Tool_mxJPO () {  }
	public Tool_mxJPO (Context context,  String[] args) throws Exception 
	{ }
	//mxMainMethod Similar to Main method of Java
	public int mxMain(Context context, String[] args) throws Exception  {System.out.println("Hello  World from Main Method");return 0;}
	//Another method to test the invocation
	public int checkCreate(Context context,  String[] args) throws Exception
	{
		String sToolId = args[0];
		String sDepartmentTypeActualName = PropertyUtil.getSchemaProperty(context, "type_Department");
		String sToolDevelopedBy = PropertyUtil.getSchemaProperty(context, "relationship_ ToolDevelopedBy");
		//forming the select clause for object and connection
		StringList slObjSelect = new StringList(DomainConstants.SELECT_ID);
		StringList slRelSelect = new StringList(DomainConstants.SELECT_RELATIONSHIP_ID);


		StringList slToolSelect = new StringList(); //forming the select clause
		String sToolTypeSelect =
		"attribute["+PropertyUtil.getSchemaProperty(context, "attribute_ToolType")+"].value";
		slToolSelect.add(sToolTypeSelect); 
		
		
		DomainObject doTool = DomainObject.newInstance(context, sToolId);
		//Querying to find connected Department objects (similar to expand bus query)
		MapList mDepartment = doTool.getRelatedObjects(context, sToolDevelopedBy, sDepartmentTypeActualName,slObjSelect, slRelSelect,false,true,(short)1,"","");
		System.out.println("Department == "+mDepartment); //For debugging purpose
		Map mpTool = doTool.getInfo(context, slToolSelect);
		System.out.println("Tool type == "+ mpTool.get(sToolTypeSelect));
		if ((sToolId != null) && (!sToolId.isEmpty())){
			if ((mDepartment == null) || (mDepartment.isEmpty()) || (mpTool.get(sToolTypeSelect) == null) )
			{
				return 1;
			}
		}
		
		return 0;
		
		
		
	}
	
	public int checkAnalysis(Context context,  String[] args) throws Exception
	{
		
		String sToolId = args[0];
		String sHardwareTypeActualName = PropertyUtil.getSchemaProperty(context, "type_HardwareProduct");
		String sToolApplicability = PropertyUtil.getSchemaProperty(context, "relationship_ToolApplicability");
		//forming the select clause for object and connection
		StringList slObjSelect = new StringList(DomainConstants.SELECT_ID);
		StringList slRelSelect = new StringList(DomainConstants.SELECT_RELATIONSHIP_ID);

		
		
		DomainObject doTool = DomainObject.newInstance(context, sToolId);
		//Querying to find connected Department objects (similar to expand bus query)
		MapList mHardware = doTool.getRelatedObjects(context, sToolApplicability, sHardwareTypeActualName,slObjSelect, slRelSelect,false,true,(short)1,"","");
		System.out.println("Department == "+mHardware); //For debugging purpose

		if ((sToolId != null) && (!sToolId.isEmpty())){
			if ((mHardware == null) || (mHardware.isEmpty())  )
			{
				return 1;
			}
		}
		
		return 0;
		
	}
	/*
	public int actionAnalysis(Context context,  String[] args) throws Exception
	{
		
		return 0;
	}
*/
}
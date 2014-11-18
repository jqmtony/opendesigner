import junit.framework.Assert;
import org.openossad.data.dao.GenericHibernateDAO;
import org.openossad.data.dao.HibernateUtil;
import org.openossad.data.domain.Tblgraphs;
import org.testng.annotations.Test;

import java.util.List;

public class TestGenericHibernateDAO {

	private class ObjClass extends GenericHibernateDAO<Tblgraphs,String>
    {

		public List<Tblgraphs> findByExample(Tblgraphs exampleInstance) {
			// TODO Auto-generated method stub
			return null;
		}};

	private ObjClass obj = new ObjClass();

		
	@Test(groups = "unit")
	public void testSetSession() {
		obj.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        Assert.assertTrue(obj.getSession().isOpen());
	}

    @Test(groups = "unit")
	public void testGetPersistentClass() {
		Assert.assertEquals(obj.getPersistentClass(),Tblgraphs.class);
	}

    @Test(groups = "unit")
	public void testFindById() {
		obj.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		obj.getSession().beginTransaction();
        if (!obj.findAll().isEmpty())
        {
            Assert.assertEquals(obj.findById("TRZ000-000000001", true).getGid(), "TRZ000-000000001");
        }
		
		
	}

    @Test(groups = "unit")
	public void testFindAll() {
		obj.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		obj.getSession().beginTransaction();
        if (!obj.findAll().isEmpty())
        {
            Assert.assertTrue(obj.findAll().get(0).getGid().contains("000-"));
        }
	}
/*
	public void testFindByExample() {
		fail("Not yet implemented");
	}

	public void testMakePersistent() {
		fail("Not yet implemented");
	}

	public void testMakeTransient() {
		fail("Not yet implemented");
	}

	public void testFlush() {
		fail("Not yet implemented");
	}

	public void testClear() {
		fail("Not yet implemented");
	}

	public void testFindByCriteria() {
		fail("Not yet implemented");
	}
*/
}

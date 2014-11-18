import junit.framework.Assert;
import org.openossad.data.dao.HibernateDAOFactory;
import org.openossad.data.dao.HibernateUtil;
import org.testng.annotations.Test;

public class TestHibernateDAOFactory{

	HibernateDAOFactory hibernateDAOFactory = new HibernateDAOFactory();

    @Test(groups = "unit")
	public final void testGetTblgraphsDAO() {
        Assert.assertNotNull(hibernateDAOFactory.getTblgraphsDAO());
	}

    @Test(groups = "unit")
	public final void testGetCurrentSession() {
		Assert.assertEquals(hibernateDAOFactory.getCurrentSession().toString(),
				HibernateUtil.getSessionFactory().getCurrentSession().toString());
	}

}

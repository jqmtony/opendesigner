import junit.framework.Assert;
import org.openossad.data.dao.HibernateUtil;
import org.testng.annotations.Test;

public class TestHibernateUtil {

	@Test(groups = "unit")
    public void testGetConfiguration() {
		Assert.assertNotNull(HibernateUtil.getConfiguration());
	}

	@Test(groups = "unit")
    public void testGetSessionFactory() {
		Assert.assertNotNull(HibernateUtil.getSessionFactory());
	}

}

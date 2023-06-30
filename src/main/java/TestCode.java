import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date dt= new Date ();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dtStr=sdf.format(dt);
		System.out.println(dtStr);
	}

}




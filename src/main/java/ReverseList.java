import java.util.ArrayList;
import java.util.List;

public class ReverseList {

	public static void main(String[] args) {
		List<Integer> l = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++) {
			l.add(i);
		}
		for (int i = l.size(); i > 0; i--) {
			System.out.println(l.get(i));
		}

	}

}

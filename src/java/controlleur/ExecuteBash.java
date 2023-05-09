package controlleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class ExecuteBash {

	public static void cmd_system(String cmd) {
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(cmd);
			p.waitFor();
		}
		catch(Exception e) {
			System.out.println("ERROR :" + cmd + e.toString());
		}
 	}

}

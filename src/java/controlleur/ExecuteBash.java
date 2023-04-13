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
	
	
private static class ProcessReader implements Callable {
		
		private InputStream inputStream;
		
		public ProcessReader(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		@Override
		public Object call() throws Exception {
			return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
		}
		
	}

	private String resultat;
	
	public ExecuteBash(String path) {
		
		//on regarde si on est sur windows ou non
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		ProcessBuilder builder = new ProcessBuilder();
		
		if(isWindows) {
			//il faut changer le fichier bat n'est pas bon
			builder.command(System.getProperty("user.dir") + "\\src\\java\\controlleur\\echo.bat");
		} else {
			builder.command("sh", "-c", System.getProperty("user.dir") + path);
		}
		
		ExecutorService pool = Executors.newSingleThreadExecutor();
		
		try {
			Process process = builder.start();
			
			ProcessReader task = new ProcessReader(process.getInputStream());
			
			Future<List<String>> future = pool.submit(task);
			
			List<String> results = future.get();
			for (String res : results) {
				System.out.println(res);
			}
			System.out.println(results.get(0));

			resultat = results.get(0);
			
			int exitCode = process.waitFor();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}
	
	public String getResultat() {
		return resultat;
	}

}

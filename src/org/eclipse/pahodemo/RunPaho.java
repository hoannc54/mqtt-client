package org.eclipse.pahodemo;

public class RunPaho {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int a = System.out.println("Your first argument is: "+args[0]);  
		
		PublishClient client = new PublishClient();
		client.setConnection();
	}

}

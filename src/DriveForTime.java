
public class DriveForTime {

	@SuppressWarnings("unused")
	public void execute(){
		//these variables represent what we will be assigning our speeds to.
		double mtrFrontRight;
		double mtrFrontLeft;
		double mtrBackRight;
		double mtrBackLeft;
		
		double speed = 3.0; //The variable we are assigning
		double yawHeading = 0; //Obtained from Robot class
		
		double getCurrentSetPoint = 0.0; //This Will be obtained via a variable set in the turn to heading class as it is obtained. (possible issues)
		double actualEncoderSetpoint; //Represents the value the encoder is actually at.
		
		double zeroHeading; //This is a weird one. it is there because of the odd difference calculations issue of say being 5 off of zero and heading being 355;
		
		
		//Switch structure stuff
		int forward = 0;
		int left = 1;
		int right = 2;
		int state = forward;
		
		
		if(getCurrentSetPoint == 0) {
			state = forward;
		} else if(getCurrentSetPoint == 90) {
			state = right;
		} else if(getCurrentSetPoint == 270) {
			state = left;
		}
		
		//where the weirdness of zero heading comes into play.
		if(yawHeading > 180){
			zeroHeading = 360 - yawHeading;
			zeroHeading = -(zeroHeading);
		} else {
			zeroHeading = yawHeading;
		}
		
		//Here comes the fun math.
		// oh yeah and brackets are fun just saying.
		//try not to get cancer.
		
		switch (state) {
			
			case 0 :
				if(zeroHeading > 5) {
					mtrFrontRight = -(speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3) );
					mtrBackRight = -(speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3) );
				} else {
					mtrFrontRight = -speed;
					mtrBackRight = -speed;
				}
				
				if(zeroHeading < -5) {
					mtrFrontLeft = speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3);
					mtrBackLeft = speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3);
				} else {
					mtrFrontLeft = speed;
					mtrBackLeft = speed;
				}
				
				break;
				
			
				
			case 1 :
				if(zeroHeading < -5) {
						mtrBackRight = -(speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3) );
						mtrBackLeft = speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3);
					} else {
						mtrBackRight = -speed;
						mtrBackLeft = speed;
					}
					
					if(zeroHeading > 5) {
						mtrFrontRight = -(speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3) );
						mtrFrontLeft = speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3);
					} else {
						mtrFrontRight = -speed;
						mtrFrontLeft = speed;
					}
				
				break;
				
				
			
			case 2 : 
				if(zeroHeading > 5) {
					mtrBackRight = -(speed - (((getCurrentSetPoint + zeroHeading) * Math.pow(10, -1)) * .3) );
					mtrBackLeft = speed - (((getCurrentSetPoint + zeroHeading) * Math.pow(10, -1)) * .3);
				} else {
					mtrBackRight = -speed;
					mtrBackLeft = speed;
				}
				
				if(zeroHeading < -5) {
					mtrFrontRight = -(speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3) );
					mtrFrontLeft = speed + (((getCurrentSetPoint - zeroHeading) * Math.pow(10, -1)) * .3);
				} else {
					mtrFrontRight = -speed;
					mtrFrontLeft = speed;
				}
				
				break;
			
		}
	}

}

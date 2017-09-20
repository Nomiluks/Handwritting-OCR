import java.io.IOException;
import java.util.Arrays;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedLearning;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TrainingSetImport;
import org.neuroph.util.TransferFunctionType;


public class nueral {
	public static char characters[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','1','2','3','4','5','6','7','8','9','0'};
	static int inc = 0;
	static double result;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create training set (logical XOR function)
		TrainingSet<SupervisedTrainingElement> trainingSet = new TrainingSet<SupervisedTrainingElement>(288, 62);

			//TrainingSet<SupervisedTrainingElement> trainingSet1 = new TrainingSet<SupervisedTrainingElement>(272, 1);
	
	/*trainingSet.addElement(new SupervisedTrainingElement(new double[]{
				0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0}
	, new double[]{1,0,0,0,0,0,0,0,0,0
			,0,0,0,0,0,0,0,0,0,0
			,0,0,0,0,0,0,0,0,0,0
			,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0
			,0,0,0,0,0,0,0,0,0,0,0
}));*///b


		try {
            trainingSet = TrainingSetImport.importFromFile("C:/Users/DEll/Desktop/test/mix fonts styles difsam12 f18  2 f66 tsetasm  testasm1 1618.txt", 288, 62, ",");
        } catch(IOException iox) {
            iox.printStackTrace();
        }
    /* MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID, 288, 72, 62);
    	  SupervisedLearning learningRule = (SupervisedLearning)myMlPerceptron.getLearningRule();
    	  learningRule.setMaxError(0.001); 
    	  learningRule.setMaxIterations(500);
    	  
    	  
    	myMlPerceptron.learn(trainingSet);
    	
    	myMlPerceptron.save("finaltraining1.nnet");*/
    	
		NeuralNetwork loadedMlPerceptron = NeuralNetwork.load("finaltraining1.nnet");
		System.out.println("Testing loaded neural network");
		double res[]=testNeuralNetwork(loadedMlPerceptron, trainingSet);
		/*for(int j=0;j<res.length;j++)
		System.out.print(res[j]);*/
	}
	public static double[] testNeuralNetwork(NeuralNetwork nnet, TrainingSet<SupervisedTrainingElement> tset) 
	{
		String output= "";
		double[] networkOutput={};
		for(TrainingElement trainingElement : tset.elements()) 
		{
			nnet.setInput(trainingElement.getInput());
			nnet.calculate();
			 networkOutput = nnet.getOutput();
			//System.out.print("Input: " + Arrays.toString(trainingElement.getInput()) );
			//System.out.println(" Output: " + Arrays.toString(networkOutput) );
			
			
			output = Arrays.toString(networkOutput);
			double get[] = convertStringToDouble(output);
			System.out.print(characters[inc%62]+"   ");
			inc++;
    		bestMatch(get);
		}
		return networkOutput;
	}

public static double[] convertStringToDouble(String str)
{
	int next=0;
	String newstr = "";
	int getLength = str.length();
	String result[] = new String [62];
	for(int i=0;i<result.length;i++)
	{
		result[i] = "";
	}
	for(int i=1;i<(getLength-1);i++)
	{
		newstr += str.charAt(i);
	}
	getLength = newstr.length();
	for(int i=0;i<(getLength);i++)
	{
		if(newstr.charAt(i) != ',')
		{
			result[next] += newstr.charAt(i);
		}
		else
		{
			//android.util.Log.i("Next"+next, result[next]);
			next++;
		}
	}
	//android.util.Log.i("Next"+25, result[25]);
	String a = "",b="";
	double twentysix[] = new double[62];
	double g =0.0;
	for(int i =0; i<62 ;i++)
	{
		a = result[i];
		if(a.length()>7)
		{
    		b += a.charAt(0);
    	    b += a.charAt(1);
    	    b += a.charAt(2);
    	    b += a.charAt(3);
    	    b += a.charAt(4);
    	    b += a.charAt(5);
    	    b += a.charAt(6);
    	    g = Double.parseDouble(b);
    	    if(g > 1 || g < 0)
    	    {
    	    	g = 0.0;
    	    }
    	    twentysix[i] = g;
		}
		else
		{
			b += a.charAt(0);
    	    b += a.charAt(1);
    	    b += a.charAt(2);
    	    b += a.charAt(3);
    	    g = Double.parseDouble(b);
    	    if(g > 1 || g < 0)
    	    {
    	    	g = 0.0;
    	    }
    	    twentysix[i] = g;
		}
    	//android.util.Log.i("double", (i+1)+": "+twentysix[i]);
    	b="";
    	a="";
    	//System.out.print(" "+twentysix[i]);
	}
	System.out.println();
	return twentysix;
}

	static void bestMatch(double twentysix[])
    {
    	double chooose = 0.0;
	    double temp    = 0.0;
	    boolean go     = false;
	    int index      = 0;
    	for(int i =0; i<62; i++)
    	{
    		if(twentysix[i]>0.0)
    		{
    			if(go == false)
    			{
    				chooose = 1-twentysix[i];
    				index = i;
    				go = true;
    			}
    			temp = 1-twentysix[i];
    			if(temp < chooose)
    			{
    				chooose = temp;
    				index = i;
    			}
    		}
    	}
    	result +=  twentysix[index];
    	System.out.println(characters[index] +"    "+twentysix[index]+"        "+index + "   result "+ result/index);
    	
    	//char a = characters[index];
    	//android.util.Log.i("Match Index", ""+a);
    }
}

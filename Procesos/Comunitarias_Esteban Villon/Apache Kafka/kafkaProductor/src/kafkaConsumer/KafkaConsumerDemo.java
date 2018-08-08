package kafkaConsumer;

public class KafkaConsumerDemo
{
	 public static void main(String[] args) 
	 {
	     SampleConsumer consumerThread = new SampleConsumer("mgka");
	     consumerThread.start();
	     
	 }
}



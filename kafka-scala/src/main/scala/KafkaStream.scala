import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object KafkaStream {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("Kafka Spark WordCount")
      .master("local[4]")
      .getOrCreate()
//    spark.sparkContext.setLogLevel("WARN")

    val kafkaDF = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "test-topic")
      .option("startingOffsets", "earliest")
      .load()

    val stringDF = kafkaDF.selectExpr("CAST(value AS STRING)")

    val words = stringDF
      .select(explode(split(col("value"), " ")).as("word"))
      .groupBy("word")
      .count()

    val query = words.writeStream
      .outputMode("complete")
      .format("console")
      .option("checkpointLocation", "/tmp/kafka-spark-checkpoint")
      .start()

    query.awaitTermination()
  }
}
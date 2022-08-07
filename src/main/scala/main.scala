import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming._

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark._
import org.postgresql.Driver
import org.joda.time.format.DateTimeFormat
import Config.Settings
import Util.Estructura
import org.apache.log4j.{Logger,Level}
object Main{

  def main(args: Array[String]) : Unit ={

    val HOST =Settings.HOST_SOCKET
    val PORT = Settings.PORT_SOCKET
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("Main")
      .config("spark.jars",Settings.RESOURCES_DRIVER)
      .getOrCreate()


    val jdbcDF = spark.read
      .format("jdbc")
      .option("url", Settings.URL)
      .option("dbtable", Settings.DBTABLE_POSTGRES)
      .option("user", Settings.USER)
      .option("password", Settings.PASSWORD)
      .option("driver",Settings.DRIVER)
      .load()
    jdbcDF.show()
    Logger.getLogger("org").setLevel(Level.FATAL)

    val ssc = new StreamingContext(spark.sparkContext,Seconds(1))
    val rows = ssc.socketTextStream(HOST,PORT)
    val rows_lines = rows
      .map(_.split("\\|").toList)
      .map(row =>{
      Estructura(
        row(0).toInt,
        row(1).toInt,
        row(2),
        row(3),
        row(4),
        row(5).toDouble,
        row(6).toDouble,
        row(7).toInt,
        row(8).toInt,
        row(9)
      )
    })
    rows_lines.print()
    rows_lines.foreachRDD({rdd =>

      import spark.implicits._
      rdd.toDF()
        .write.format("jdbc")
        .option("url", Settings.URL)
        .option("dbtable", Settings.DBTABLE_POSTGRES)
        .option("user", Settings.USER)
        .option("password", Settings.PASSWORD)
        .option("driver",Settings.DRIVER).mode("append").save()
    })

    ssc.start()
    ssc.awaitTermination()
  }
}



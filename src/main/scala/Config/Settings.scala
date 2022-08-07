package Config

object Settings {
  val HOST_SOCKET ="localhost"
  val PORT_SOCKET =<port>
  val PORT_POSTGRES =5432
  val HOST_POSTGRES ="<host-aws>"
  val DATABASE_POSTGRES = "postgres"
  val RESOURCES_DRIVER ="resources/postgresql-42.4.0.jar"
  val DBTABLE_POSTGRES ="<db-aws>"
  val USER ="<user>"
  val PASSWORD ="<password>"
  val DRIVER ="org.postgresql.Driver"
  val URL ="jdbc:postgresql://"+HOST_POSTGRES+":"+PORT_POSTGRES+"/"+DATABASE_POSTGRES

}

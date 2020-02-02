import kafka.Functions.createPersonJSON
import kafka.Functions.createProducer
import org.apache.kafka.clients.producer.ProducerRecord

fun main() {

    // kafka-topics --zookeeper localhost:2181 --create --topic persons --replication-factor 1 --partitions 4
    // kafka-topics.sh --zookeeper localhost:2181 --list
    // kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic persons --from-beginning

    val producer = createProducer("localhost:9092")
    val personsTopic = "persons"

    while (true) {

        // Create fake Person object and serialize to JSON
        val fakePersonJSON = createPersonJSON()

        // Send data to local broker
        val futureResult = producer.send(ProducerRecord(personsTopic, fakePersonJSON))

        // Wait 2 seconds, then stop until write acknowledgment. Given that producer.acks = 1 we ensure that the message will be persisted before sending another
        Thread.sleep(2000)
        futureResult.get()
    }
}

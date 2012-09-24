package org.streamml.lsh

import backtype.storm.topology.{OutputFieldsDeclarer, IRichBolt}
import backtype.storm.task.{TopologyContext, OutputCollector}
import backtype.storm.tuple.{Fields, Tuple}

class LSHBolt extends IRichBolt {

  var _collector: OutputCollector = _
  var _context: TopologyContext = _

  def prepare(ctx: java.util.Map[_,_], tc: TopologyContext, oc: OutputCollector) {
    _collector = oc
    _context = tc
  }

  def execute(tuple: Tuple) {
    val url = tuple.getString(0)
    val city = tuple.getString(1)
    val browser = tuple.getString(2)
    println("Got back url = " + url + " city = " + city + " browswer = " + browser)
    _collector.ack(tuple)
  }

  def declareOutputFields(o: OutputFieldsDeclarer) {
    o.declare(new Fields("bla"))
  }

  def getComponentConfiguration  = null
  def cleanup() = null

}
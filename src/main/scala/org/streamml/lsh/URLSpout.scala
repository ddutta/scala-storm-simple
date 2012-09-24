package org.streamml.lsh

import backtype.storm.topology.base.BaseRichSpout
import backtype.storm.task.TopologyContext
import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.tuple.{Fields, Values}
import util.Random
import backtype.storm.topology.OutputFieldsDeclarer

class URLSpout extends BaseRichSpout {

  var _context:TopologyContext = _
  var _collector:SpoutOutputCollector = _

  val Urls = List("http://www.cnn.com/", "http://nytimes.com/",
    "http://venturebeat.com", "http://mercurynews.com", "http://www.espn3.com/today/",
    "http://gmail.com")
  val Cities = List("San Jose", "San Francisco", "Los Angeles", "Dallas", "Chicago", "Seattle")
  val Browsers = List("IE7", "IE8", "IE9", "Firefox4", "Chrome", "Safari", "Mobile Safari", "Android Browser")

  def nextTuple() {
    Thread.sleep(100)
    _collector.emit(new Values(Urls(Random.nextInt(Urls.length)),
      Cities(Random.nextInt(Cities.length)),
      Browsers(Random.nextInt(Browsers.length))))
  }

  def open(conf: java.util.Map[_,_], context: TopologyContext, collector: SpoutOutputCollector) = {
    _collector = collector
    _context = context
  }

  def declareOutputFields(ofd: OutputFieldsDeclarer) {
    ofd.declare(new Fields("url", "city","browser"))
  }

  def isDistributed() = false
}


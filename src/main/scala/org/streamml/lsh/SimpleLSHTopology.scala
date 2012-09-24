package org.streamml.lsh

import backtype.storm.topology.{TopologyBuilder, OutputFieldsDeclarer, IRichBolt, IRichSpout}
import java.util
import backtype.storm.task.{OutputCollector, TopologyContext}
import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.tuple.{Fields, Values, Tuple}
import backtype.storm.topology.base.BaseRichSpout
import scala.util.Random
import backtype.storm.{LocalCluster, Config}
import backtype.storm.utils.Utils


object SimpleLSHTopology {

  def main(args: Array[String]) = {
    val builder: TopologyBuilder = new TopologyBuilder
    val conf: Config = new Config
    conf.setDebug(true)
    builder.setSpout("urlspout", new URLSpout(), 3)
    builder.setBolt("lshbolt", new LSHBolt(), 3).shuffleGrouping("urlspout")
    val cluster: LocalCluster = new LocalCluster()
    cluster.submitTopology("test", conf, builder.createTopology())
    Utils.sleep(100000);
    cluster.killTopology("test");
    cluster.shutdown();
  }

}

// vim: set ts=2 sw=2 et:

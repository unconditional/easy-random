/*
 * The MIT License
 *
 *   Copyright (c) 2016, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package io.github.benas.randombeans.beans;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class CompositeCollectionBean {

    private List<List<String>> listOfLists;
    private ArrayList<LinkedList<Person>> typedListOfLists;

    private Set<Set<String>> setOfSets;
    private HashSet<LinkedHashSet<String>> typedSetOfSets;

    private Queue<Queue<String>> queueOfQueues;
    private LinkedBlockingQueue<Queue<String>> typedQueueOdQueues;

    public Set<Set<String>> getSetOfSets() {
        return setOfSets;
    }

    public void setSetOfSets(Set<Set<String>> setOfSets) {
        this.setOfSets = setOfSets;
    }

    public Queue<Queue<String>> getQueueOfQueues() {
        return queueOfQueues;
    }

    public void setQueueOfQueues(Queue<Queue<String>> queueOfQueues) {
        this.queueOfQueues = queueOfQueues;
    }

    public List<List<String>> getListOfLists() {
        return listOfLists;
    }

    public void setListOfLists(List<List<String>> listOfLists) {
        this.listOfLists = listOfLists;
    }

    public ArrayList<LinkedList<Person>> getTypedListOfLists() {
        return typedListOfLists;
    }

    public void setTypedListOfLists(ArrayList<LinkedList<Person>> typedListOfLists) {
        this.typedListOfLists = typedListOfLists;
    }

    public HashSet<LinkedHashSet<String>> getTypedSetOfSets() {
        return typedSetOfSets;
    }

    public void setTypedSetOfSets(HashSet<LinkedHashSet<String>> typedSetOfSets) {
        this.typedSetOfSets = typedSetOfSets;
    }

    public LinkedBlockingQueue<Queue<String>> getTypedQueueOdQueues() {
        return typedQueueOdQueues;
    }

    public void setTypedQueueOdQueues(LinkedBlockingQueue<Queue<String>> typedQueueOdQueues) {
        this.typedQueueOdQueues = typedQueueOdQueues;
    }
}

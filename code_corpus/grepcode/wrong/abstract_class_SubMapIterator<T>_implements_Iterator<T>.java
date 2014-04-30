
        abstract class SubMapIterator<T> implements Iterator<T> {
            TreeMap.Entry<K,V> lastReturned;
            TreeMap.Entry<K,V> next;
            final Object fenceKey;
            int expectedModCount;

            SubMapIterator(TreeMap.Entry<K,V> first,
                           TreeMap.Entry<K,V> fence) {
                expectedModCount = m.modCount;
                lastReturned = null;
                next = first;
                fenceKey = fence == null ? UNBOUNDED : fence.key;
            }

            public final boolean hasNext() {
                return next != null && next.key != fenceKey;
            }

            final TreeMap.Entry<K,V> nextEntry() {
                TreeMap.Entry<K,V> e = next;
                if (e == null || e.key == fenceKey)
                    throw new NoSuchElementException();
                if (m.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                next = successor(e);
                lastReturned = e;
                return e;
            }

            final TreeMap.Entry<K,V> prevEntry() {
                TreeMap.Entry<K,V> e = next;
                if (e == null || e.key == fenceKey)
                    throw new NoSuchElementException();
                if (m.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                next = predecessor(e);
                lastReturned = e;
                return e;
            }

            final void removeAscending() {
                if (lastReturned == null)
                    throw new IllegalStateException();
                if (m.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                // deleted entries are replaced by their successors
                if (lastReturned.left != null && lastReturned.right != null)
                    next = lastReturned;
                m.deleteEntry(lastReturned);
                lastReturned = null;
                expectedModCount = m.modCount;
            }

            final void removeDescending() {
                if (lastReturned == null)
                    throw new IllegalStateException();
                if (m.modCount != expectedModCount)
                    throw new ConcurrentModificationException();
                m.deleteEntry(lastReturned);
                lastReturned = null;
                expectedModCount = m.modCount;
            }

        }

        final class SubMapEntryIterator extends SubMapIterator<Map.Entry<K,V>> {
            SubMapEntryIterator(TreeMap.Entry<K,V> first,
                                TreeMap.Entry<K,V> fence) {
                super(first, fence);
            }
            public Map.Entry<K,V> next() {
                return nextEntry();
            }
            public void remove() {
                removeAscending();
            }
        }

        final class SubMapKeyIterator extends SubMapIterator<K> {
            SubMapKeyIterator(TreeMap.Entry<K,V> first,
                              TreeMap.Entry<K,V> fence) {
                super(first, fence);
            }
            public K next() {
                return nextEntry().key;
            }
            public void remove() {
                removeAscending();
            }
        }

        final class DescendingSubMapEntryIterator extends SubMapIterator<Map.Entry<K,V>> {
            DescendingSubMapEntryIterator(TreeMap.Entry<K,V> last,
                                          TreeMap.Entry<K,V> fence) {
                super(last, fence);
            }

            public Map.Entry<K,V> next() {
                return prevEntry();
            }
            public void remove() {
                removeDescending();
            }
        }

        final class DescendingSubMapKeyIterator extends SubMapIterator<K> {
            DescendingSubMapKeyIterator(TreeMap.Entry<K,V> last,
                                        TreeMap.Entry<K,V> fence) {
                super(last, fence);
            }
            public K next() {
                return prevEntry().key;
            }
            public void remove() {
                removeDescending();
            }
        }
    }

    
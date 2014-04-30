
        public boolean contentsLost() {
            if (backBuffers == null) {
                return false;
            } else {
                return backBuffers[backBuffers.length - 1].contentsLost();
            }
        }

        
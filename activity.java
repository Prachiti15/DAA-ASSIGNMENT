class activity {
    int start, finish, difference;

    activity(int start, int finish) {
        this.start = start;
        this.finish = finish;
        this.difference = finish - start;
    }

    @Override
    public String toString() {
        return "(" + this.start + ", " + this.finish + ", " + this.difference + ") ";
    }
}
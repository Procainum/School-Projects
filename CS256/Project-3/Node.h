#ifndef NODE_H
#define NODE_H

template <class T>
class Node {
   public:
      Node ();

      Node (const T& value, Node<T>* next);

      ~Node ();

      T getValue () const;
      void setValue (const T& value);
      Node<T>* getNext () const;
      void setNext (Node<T>* next);

   private:
      T value_;
      Node<T>* next_;
};

template <class T>
Node<T>::Node () : next_(NULL) { 
}

template <class T>
Node<T>::Node (const T& value, Node<T>* next) :
   value_(value), next_(next) {
}

template <class T>
Node<T>::~Node () {
   this->next_ = NULL;
}

template <class T>
T Node<T>::getValue () const {
   return this->value_;
}

template <class T>
void Node<T>::setValue (const T& value) {
   this->value_ = value;
}

template <class T>
Node<T>* Node<T>::getNext() const {
   return this->next_;
}

template <class T>
void Node<T>::setNext (Node<T>* next) {
   this->next_ = next;
}

#endif

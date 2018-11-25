#ifndef QUEUE_H
#define QUEUE_H

#include "Node.h"

template <class T>
class Queue {
  public:
    Queue ();
    ~Queue ();

    bool enqueue (const T& value);
    bool dequeue (T& value);
    int length () const;

  private:
    Node<T>* head_;
    Node<T>* tail_;
    int length_;
};


template <class T>
Queue<T>::Queue () : head_(NULL), length_(0) {
}

template <class T>
Queue<T>::~Queue () {
   Node<T>* temp = this->head_;
   while (temp != NULL) {
      this->head_ = this->head_->getNext();
      delete temp;
      temp = this->head_;
   }
}

template <class T>
bool Queue<T>::enqueue (const T& value) {
   Node<T>* node = new Node<T> (value, NULL);
   if(this->head_ == NULL) {
     this->head_ = node;
     this->tail_ = node;
     ++this->length_;
     return true;
   }
   if(this->tail_ != NULL) {
     this->tail_->setNext(node);
   }
   this->tail_ = node;
   ++this->length_;
   return true;
}

template <class T>
bool Queue<T>::dequeue (T& value) {
   if (this->head_ == NULL) return false;
   Node<T>* node = this->head_;
   if(this->head_->getNext() == NULL) {
     this->head_ = NULL;
   } else {
     this->head_ = this->head_->getNext();
   }
   value = node->getValue();
   delete node;
   --this->length_;
   return true;
}

template <class T>
int Queue<T>::length() const {
   return this->length_;
}

#endif


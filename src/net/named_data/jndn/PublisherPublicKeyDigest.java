/**
 * Copyright (C) 2013 Regents of the University of California.
 * @author: Jeff Thompson <jefft0@remap.ucla.edu>
 * See COPYING for copyright and distribution information.
 */

package net.named_data.jndn;

import net.named_data.jndn.util.Blob;

/**
 * A PublisherPublicKeyDigest holds the publisher public key digest value, if any.
 * We make a separate class since this is used by multiple other classes.
 */
public class PublisherPublicKeyDigest {
  /**
   * Create a new PublisherPublicKeyDigest with an empty value.
   */
  public PublisherPublicKeyDigest()
  {  
  }

  /**
   * Create a new PublisherPublicKeyDigest with a copy of the value in the given publisherPublicKeyDigest.
   * @param publisherPublicKeyDigest The PublisherPublicKeyDigest to copy.
   */
  public PublisherPublicKeyDigest(PublisherPublicKeyDigest publisherPublicKeyDigest)
  {  
    publisherPublicKeyDigest_ = publisherPublicKeyDigest.publisherPublicKeyDigest_;
  }

  public final Blob 
  getPublisherPublicKeyDigest() { return publisherPublicKeyDigest_; }

  public final void 
  setPublisherPublicKeyDigest(Blob publisherPublicKeyDigest) 
  {
    publisherPublicKeyDigest_ = (publisherPublicKeyDigest == null ? new Blob() : publisherPublicKeyDigest);
  }

  /**
   * Clear the publisherPublicKeyDigest.
   */
  public final void 
  clear()
  {
    publisherPublicKeyDigest_ = new Blob();
  }

  private Blob publisherPublicKeyDigest_ = new Blob();
}
